package it.unicam.cs.ids.TerraViva.Security.Authentication;

import it.unicam.cs.ids.TerraViva.Models.Role;

public record AuthenticationResponse(String token, String username, Role role) {}