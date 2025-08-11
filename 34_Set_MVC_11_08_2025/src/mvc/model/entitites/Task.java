package mvc.model.entitites;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {
	private String content;
	private LocalDateTime createdAt;

	public Task(String content, LocalDateTime createdAt) {
		if (content == null || content.isBlank()) {
			throw new IllegalArgumentException("Task content must not be blank");
		}
		this.content = content;
		this.createdAt = createdAt;
	}

	public Task(String content) {
		this(content, LocalDateTime.now());
	}

	public String getContent() {
		return content;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	@Override
	public String toString() {
		return "Task [content=" + content + ", createdAt=" + createdAt + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, createdAt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(content, other.content) && Objects.equals(createdAt, other.createdAt);
	}

}
