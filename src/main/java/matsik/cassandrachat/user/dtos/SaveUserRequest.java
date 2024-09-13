package matsik.cassandrachat.user.dtos;

public record SaveUserRequest(String username, String email, String name, String surname, String password, String role) {
}
