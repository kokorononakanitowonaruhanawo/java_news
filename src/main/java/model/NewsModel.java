package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class NewsModel implements Serializable{
	private int id;
	private int genre_id;
	private int adnim_id;
	private String title;
	private String article;
	private String picture;
	private String url;
	private String twitter;
    private Date registration_date;
	private int is_deleted;
	private Timestamp created_at;
	private Timestamp update_at;
	private AdminModel admin;
	private GenreModel genre;
	
//	id
	public int getId() {return this.id;}
	public void setId(int id) {this.id = id;}
	
//	genre_id
	public int getGenreId() {return this.genre_id;}
	public void setGenreId(int genre_id) {this.genre_id = genre_id;}
	
//	admin_id
	public int getAdminId() {return this.adnim_id;}
	public void setAdminId(int admin_id) {this.adnim_id = admin_id;}
	
//	title
	public String getTitle() {return this.title;}
	public void setTitle(String title) {this.title = title;}
	
//	article
	public String getArticle() {return this.article;}
	public void setArticle(String article) {this.article = article;}
	
//	picture
	public String getPicture() {return this.picture;}
	public void setPicture(String picture) {this.picture = picture;}
	
//	url
	public String getURL() {return this.url;}
	public void setURL(String url) {this.url = url;}
	
//	twitter
	public String getTwitter() {return this.twitter;}
	public void setTwitter(String twitter) {this.twitter = twitter;}
	
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
	
//	AdminModel
	public AdminModel getAdminModel() {return this.admin;}
	public void setAdminModel(AdminModel admin) {this.admin = admin;}
	
//	GenreModel
	public GenreModel getGenreModel() {return this.genre;}
	public void setGenreModel(GenreModel genre) {this.genre = genre;}
	 
	
}
