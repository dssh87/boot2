package org.zerock.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tbl_profile")
@Data
public class Profile {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long fno;
	
	private String fname;
	
	private boolean current;
	@ManyToOne
	private Member member;
	
	
}