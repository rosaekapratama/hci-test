package co.id.hci.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rosaekapratama@gmail.com
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class GroupModuleId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1123561190475901972L;

	@Column(name = "group_id")
	private Long groupId;
	
	@Column(name = "module_id")
	private Long moduleId;
	
}
