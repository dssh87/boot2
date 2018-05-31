package org.zerock.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tbl_member")
@Data
public class Member {
	
	@Id
	private String uid;
	private String upw;
	private String uname;
}