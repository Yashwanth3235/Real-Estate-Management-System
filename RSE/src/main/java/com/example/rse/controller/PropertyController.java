
package com.example.rse.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.rse.model.Property;
import com.example.rse.model.User;
import com.example.rse.service.PropertyService;
import com.example.rse.service.UserService;

@CrossOrigin(origins = "http://localhost:3000") // Allow CORS for your frontend
@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private final PropertyService propertyService;

    @Autowired
    private final UserService userService;

    public PropertyController(PropertyService propertyService, UserService userService) {
        this.propertyService = propertyService;
        this.userService = userService;
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> createProperty(
            @RequestParam("email") String email,
            @RequestParam("imageUrl") MultipartFile imageFile,
            @RequestParam("bhkType") String bhkType,
            @RequestParam("depositPrice") double depositPrice,
            @RequestParam("location") String location,
            @RequestParam("description") String description,
            @RequestParam("ownerName") String ownerName,
            @RequestParam("ownerContact") Long ownerContact,
            @RequestParam("propertyStatus") String propertyStatus) {

        // Debugging statements to log each received parameter
        System.out.println("email: " + email);
        System.out.println("bhkType: " + bhkType);
        System.out.println("depositPrice: " + depositPrice);
        System.out.println("location: " + location);
        System.out.println("description: " + description);
        System.out.println("ownerName: " + ownerName);
        System.out.println("ownerContact: " + ownerContact);
        System.out.println("propertyStatus: " + propertyStatus);

        // Check if the file is not empty
        if (imageFile != null && !imageFile.isEmpty()) {
            System.out.println("Image file received: " + imageFile.getOriginalFilename());
        } else {
            System.out.println("No image file received");
        }

        // Find the user by email
        Optional<User> user = userService.findByEmail(email);

        // Check if user is an agent
        if (user.isPresent() && "agent".equalsIgnoreCase(user.get().getRole())) {
            try {
                // Create a new property entity
                Property property = new Property();
                property.setImageUrl(imageFile.getBytes()); // Save image as byte array
                property.setBhkType(bhkType);
                property.setDepositPrice(depositPrice);
                property.setLocation(location);
                property.setDescription(description);
                property.setOwnerName(ownerName);
                property.setOwnerContact(ownerContact);
                property.setPropertyStatus(propertyStatus);

                // Save the property and return its ID
                Property savedProperty = propertyService.saveProperty(property);
                System.out.println("Property saved successfully with ID: " + savedProperty.getId());
                return ResponseEntity.ok(savedProperty.getId());
            } catch (IOException e) {
                System.out.println("Error processing the file: " + e.getMessage());
                return ResponseEntity.status(500).body("Error processing the file");
            }
        }

        // If the user is not an agent or user not found, return 403
        System.out.println("Unauthorized: User not found or not an agent");
        return ResponseEntity.status(403).body("Unauthorized to create property listing");
    }


	@GetMapping("/image/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<byte[]> getPropertyImage(@PathVariable Long id) {
		Optional<Property> property = propertyService.findById(id);

		if (property.isPresent()) {
			byte[] image = property.get().getImageUrl();
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG) // Use MediaType.IMAGE_PNG if needed
					.body(image);
		}

		return ResponseEntity.status(404).body(null);
	}

	@PutMapping("/update/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> updateProperty(@PathVariable Long id, @RequestBody Property updatedProperty,
			@RequestParam String username) {
		Optional<User> user = userService.findByEmail(username);

		if (user.isPresent() && "agent".equalsIgnoreCase(user.get().getRole())) {
			Optional<Property> existingProperty = propertyService.findById(id);

			if (existingProperty.isPresent()) {
				Property property = existingProperty.get();
				property.setImageUrl(updatedProperty.getImageUrl());
				property.setBhkType(updatedProperty.getBhkType());
				property.setDepositPrice(updatedProperty.getDepositPrice());
				property.setLocation(updatedProperty.getLocation());
				property.setDescription(updatedProperty.getDescription());
				property.setOwnerName(updatedProperty.getOwnerName());
				property.setOwnerContact(updatedProperty.getOwnerContact());
				property.setPropertyStatus(updatedProperty.getPropertyStatus());

				Property savedProperty = propertyService.saveProperty(property);
				return ResponseEntity.ok(savedProperty); // Return property ID
			}

			return ResponseEntity.status(404).body("Property not found");
		}

		return ResponseEntity.status(403).body("Unauthorized to update property listing");
	}

	@DeleteMapping("/delete/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> deleteProperty(@PathVariable Long id, @RequestParam String username) {
		Optional<User> user = userService.findByEmail(username);

		if (user.isPresent() && "agent".equalsIgnoreCase(user.get().getRole())) {
			Optional<Property> property = propertyService.findById(id);

			if (property.isPresent()) {
				propertyService.deletePropertyById(id);
				return ResponseEntity.ok("Property deleted successfully");
			}

			return ResponseEntity.status(404).body("Property not found");
		}

		return ResponseEntity.status(403).body("Unauthorized to delete property listing");
	}

	@GetMapping("/get/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> getPropertyById(@PathVariable Long id) {
		Optional<Property> property = propertyService.findById(id);

		if (property.isPresent()) {
			return ResponseEntity.ok(property.get());
		}

		return ResponseEntity.status(404).body("Property not found");
	}

	@GetMapping("/all")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> getAllProperties() {
		return ResponseEntity.ok(propertyService.findAll());
	}

	@GetMapping("/properties")
	public List<Property> getPropertiesByOwnerName(@RequestParam String ownerName) {
		return propertyService.getPropertiesByOwnerName(ownerName);
	}

	@GetMapping("/view")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> viewProperties(@RequestParam String username) {
		Optional<User> user = userService.findByEmail(username);

		if (user.isPresent() && ("client".equalsIgnoreCase(user.get().getRole())
				|| "agent".equalsIgnoreCase(user.get().getRole()))) {
			List<Property> properties = propertyService.findAll();
			return ResponseEntity.ok(properties);
		}

		return ResponseEntity.status(403).body("Unauthorized to view properties");
	}
}
