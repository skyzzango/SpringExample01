package study.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
@Getter
@Setter
public class User {

	@Id
	@Column(length = 10)
	String id;

	@Column(length = 20, nullable = false)
	String name;

	@Column(length = 20, nullable = false)
	String password;
}
