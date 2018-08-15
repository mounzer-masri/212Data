package com.application.model;

import com.domain.models.WunderUser;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Munzri Masri on 12.8.2018.
 */
@Entity
public class UserAccessToken implements Serializable {
	@Id
	@Column(name = "token", nullable = false)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String token;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private WunderUser wunderUser;

	@Column(name = "create_date", nullable = false)
	private Date createDate = new Date();

	@Column(name = "expire_date", nullable = true)
	private Date expireDate;

	public UserAccessToken() {
	}

	public UserAccessToken(WunderUser wunderUser) {
		this.wunderUser = wunderUser;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public WunderUser getWunderUser() {
		return wunderUser;
	}

	public void setWunderUser(WunderUser wunderUser) {
		this.wunderUser = wunderUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	@Transient
	public boolean isExpired() {
		return expireDate != null && expireDate.compareTo(new Date()) < 0;
	}
}
