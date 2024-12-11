package com.feedback.service;

import com.feedback.model.User;
import java.security.SecureRandom;
import com.feedback.repository.UserRepo;
import com.feedback.request.ResetPasswordRequest;
import com.feedback.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    private static final SecureRandom random = new SecureRandom();
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    
    // Register a new user
    public ResponseEntity<String> register(User user) {
          if (userRepo.findByUsername(user.getUsername()).isPresent()) {
        logger.warn("User registration failed: Username {} already exists", user.getUsername());
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
    }
        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            logger.warn("User registration failed: Email {} already exists", user.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this email already exists");
        }

        // Validate email format
        if (!isValidEmail(user.getEmail())) {
            logger.warn("User registration failed: Invalid email format for email: {}", user.getEmail());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email format");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        logger.info("User registered successfully with email: {}", user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    // Login a user
    public ResponseEntity<LoginResponse> login(User user) {
            Optional<User> existingUser = userRepo.findByUsername(user.getUsername())
            .or(() -> userRepo.findByEmail(user.getEmail()));
        if (existingUser.isEmpty()) {
            logger.warn("Login failed: User not found for email: {}", user.getEmail());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LoginResponse("User not found",null));
        }

        User foundUser = existingUser.get();
        if (!passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            logger.warn("Login failed: Incorrect password for email: {}", user.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Incorrect password", null));
        }
        if (!foundUser.getUserType().equals(user.getUserType())) {
            logger.warn("Login failed: Invalid user type for email: {}", user.getEmail());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponse("Invalid user type", null));
        }

        logger.info("User logged in successfully: {}", user.getEmail());
        return ResponseEntity.ok(new LoginResponse(foundUser, "Login successful"));
    }

    // Send password reset verification code
    public ResponseEntity<String> sendVerificationCode(String email) {
        Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            String verificationCode = generateVerificationCode(4); // Generate a 4-digit code
            user.setVerificationCode(verificationCode); // Save the verification code to the user
            userRepo.save(user);
            emailService.sendVerificationEmail(user.getEmail(), verificationCode);

            logger.info("Verification code sent to email: {}", user.getEmail());
            return ResponseEntity.ok("Verification code sent to email");
        } else {
            logger.warn("Password reset request failed: User with email {} does not exist", email);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with the given email does not exist");
        }
    }

    // Reset password
    public ResponseEntity<String> resetPassword(ResetPasswordRequest request) {
        Optional<User> userOpt = userRepo.findByEmail(request.getEmail());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Compare codes in a case-insensitive manner
            if (user.getVerificationCode() != null && user.getVerificationCode().equals(request.getVerificationCode())) {
                user.setPassword(passwordEncoder.encode(request.getNewPassword()));
                user.setVerificationCode(null); // Clear the verification code after use
                userRepo.save(user);

                logger.info("Password reset successfully for user: {}", user.getEmail());
                return ResponseEntity.ok("Password reset successfully");
            } else {
                logger.warn("Password reset failed: Invalid verification code for email: {}", request.getEmail());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid verification code");
            }
        } else {
            logger.warn("Password reset failed: User not found for email: {}", request.getEmail());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    // Helper method to generate a 4 or 5 digit verification code
    private String generateVerificationCode(int length) {
        int code = random.nextInt((int) Math.pow(10, length)); // Generate a number with the specified number of digits
        return String.format("%0" + length + "d", code); // Format it to ensure leading zeros are added if needed
    }

    // Validate email format using regex
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // PUT: Update user by ID
    public ResponseEntity<String> updateUser(Long id, User updatedUser) {
        Optional<User> userOpt = userRepo.findById(id);
        if (userOpt.isPresent()) {
            User existingUser = userOpt.get();
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setMobile(updatedUser.getMobile());
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            existingUser.setUserType(updatedUser.getUserType());
            userRepo.save(existingUser);

            logger.info("User updated successfully: {}", existingUser.getEmail());
            return ResponseEntity.ok("User updated successfully.");
        } else {
            logger.warn("Update failed: User not found for ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

    // PATCH: Partially update user by ID
    public ResponseEntity<String> patchUser(Long id, Map<String, Object> updates) {
        Optional<User> userOpt = userRepo.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            updates.forEach((key, value) -> {
                switch (key) {
                    case "username": user.setUsername((String) value); break;
                    case "email": user.setEmail((String) value); break;
                    case "mobile": user.setMobile((String) value); break;
                    case "password": user.setPassword(passwordEncoder.encode((String) value)); break;
                    case "userType": user.setUserType((String) value); break;
                }
            });
            userRepo.save(user);

            logger.info("User partially updated: {}", user.getEmail());
            return ResponseEntity.ok("User partially updated successfully.");
        } else {
            logger.warn("Partial update failed: User not found for ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

    // HEAD: Check if user exists by ID
    public ResponseEntity<Void> headUser(Long id) {
        if (userRepo.existsById(id)) {
            return ResponseEntity.ok().build();
        } else {
            logger.warn("User existence check failed: User not found for ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // DELETE: Delete user by ID
    public ResponseEntity<String> deleteUser(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            userRepo.deleteById(id);
            logger.info("User deleted successfully: {}", id);
            return ResponseEntity.ok("User deleted successfully.");
        } else {
            logger.warn("Delete failed: User not found for ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

    // GET: Fetch all users
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // GET: Fetch user by ID
    public ResponseEntity<User> getUserById(Long id) {
        Optional<User> userOpt = userRepo.findById(id);
        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        } else {
            logger.warn("Fetch failed: User not found for ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // DELETE: Delete all users
    public ResponseEntity<String> deleteAllUsers() {
        userRepo.deleteAll();
        logger.info("All users deleted successfully.");
        return ResponseEntity.ok("All users deleted successfully.");
    }
}
