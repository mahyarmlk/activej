package adder;

import io.activej.serializer.annotations.SerializeRecord;

public class AdderCommands {
	@SerializeRecord
	public record AddRequest(long userId, float delta) {
	}

	public enum AddResponse {
		INSTANCE
	}

	@SerializeRecord
	public record GetRequest(long userId) {}

	@SerializeRecord
	public record GetResponse(float sum) {}
}
