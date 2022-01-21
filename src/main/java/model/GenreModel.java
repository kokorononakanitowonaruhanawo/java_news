package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Genre Mode
 */
public class GenreModel implements Serializable {

	private int id;
	private String genre;
	private Date registration_date;
	private int is_deleted;
	private Timestamp created_at;
	private Timestamp update_at;
	
//	id
	public int getId() {return this.id;}
	public void setId(int id) {this.id = id;}
	
//	name
	public String getGenre(){return this.genre;}
	public void setGenre(String genre) {this.genre = genre;}
	
//	registration_date
	public Date getRegistrationDate() {return this.registration_date;}
	public void setRegistrationDate(Date registration_date) {this.registration_date = registration_date;}
	
//	is_deleted
	public int getIsDeleted() {return this.is_deleted;}
	public void setIsDeleted(int is_deleted) {this.is_deleted = is_deleted;}
	
//	create_at
	public Timestamp getCreateAt() {return this.created_at;}
	public void setCreateAt(Timestamp create_at) {this.created_at = create_at;}
	
//	update_at
	public Timestamp getUpdateAt() {return this.update_at;}
	public void setUpdateAt(Timestamp update_at) {this.update_at = update_at;}
}
