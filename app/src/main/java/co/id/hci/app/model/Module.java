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

import lombok.Data;

/**
 * @author rosaekapratama@gmail.com
 *
 */
@Data
@Entity
@Table(name = "m_module")
public class Module {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<GroupModule> groupModules = new ArrayList<GroupModule>();
	
	@Column(name = "name", nullable = false, length = 16)
	private String name;
	
	/**
	 * Add more field for module here
	 * 
	 * @Column(name = "etc")
	 * private String etc;
	*/
}
