package nextstep.subway.member.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;

import nextstep.subway.member.domain.Member;

public class MemberResponse {
	private Long id;

	@JsonDeserialize(using = StringDeserializer.class)
    @JsonSerialize(using = StringSerializer.class)
	private String email;

	@JsonDeserialize(using = NumberDeserializers.IntegerDeserializer.class)
	@JsonSerialize(using = NumberSerializers.IntegerSerializer.class)
	private Integer age;

	public MemberResponse() {
	}

	public MemberResponse(Long id, String email, Integer age) {
		this.id = id;
		this.email = email;
		this.age = age;
	}

	public static MemberResponse of(Member member) {
		return new MemberResponse(member.getId(), member.getEmail(), member.getAge());
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public Integer getAge() {
		return age;
	}
}
