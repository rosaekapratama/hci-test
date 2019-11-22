package co.id.hci.app.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
@Entity
@Table(name = "m_group_module", uniqueConstraints = @UniqueConstraint(columnNames = {"group_id", "module_order"}))
public class GroupModule {

	@EmbeddedId
	@JsonIgnore
	private GroupModuleId id;
	
	@ManyToOne
    @MapsId("groupId")
	@JsonIgnore
    private Group group;
 
    @ManyToOne
    @MapsId("moduleId")
	@JsonIgnore
    private Module module;
    
    @Column(name = "module_order", nullable = false)
	@JsonProperty("moduleOrder")
	private int order;

	@JsonProperty("moduleName")
    public String getModuleName() {
    	return module.getName();
    }
}
