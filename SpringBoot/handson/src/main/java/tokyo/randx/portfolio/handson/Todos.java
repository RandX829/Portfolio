package tokyo.randx.portfolio.handson;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Todos(List<Todo> todos) { }
