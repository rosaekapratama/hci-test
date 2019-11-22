package co.id.hci.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author rosaekapratama@gmail.com
 *
 */
@Data
@Entity
@Table(name = "m_group")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonProperty("modules")
	private List<GroupModule> groupModules = new ArrayList<GroupModule>();
	
	@Column(name = "name", nullable = false, length = 16)
	@JsonIgnore
	private String name;

	/**
	 * Add more field for group here
	 * 
	 * @Column(name = "etc")
	 * private String etc;
	*/
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		if(other.getGroupModules()==null)
			return false;
		if(other.getGroupModules().size()!=groupModules.size())
			return false;
		for(GroupModule t : groupModules) {
			if(!other.getGroupModules().contains(t))
				return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupModules == null) ? 0 : groupModules.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
}
