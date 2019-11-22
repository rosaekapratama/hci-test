package co.id.hci.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author rosaekapratama@gmail.com
 *
 */
@Data
@Entity
@Table(name = "m_user")
public class User {

	@Id
	@Column(name = "username", unique = true, nullable = false, length = 16)
	private String username;
	
	/**
	 * I only use unidirectional many to one here for anticipation of a lot of data
	 */
	@ManyToOne
	@JoinColumn(name = "group_id", nullable = false)
	private Group group;
	
	@Column(name = "joined_date", nullable = false)
	private Date date;

	/**
	 * Add more field for user here
	 * 
	 * @Column(name = "etc")
	 * private String etc;
	*/
	
}
