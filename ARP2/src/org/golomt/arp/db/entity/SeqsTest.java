package org.golomt.arp.db.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SEQS_TEST database table.
 * 
 */
@Entity
@Table(name="SEQS_TEST")
@NamedQuery(name="SeqsTest.findAll", query="SELECT s FROM SeqsTest s")
public class SeqsTest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String name;

	public SeqsTest() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}