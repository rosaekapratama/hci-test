package co.id.hci.app.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.id.hci.app.model.Group;
import co.id.hci.app.model.GroupModule;
import co.id.hci.app.model.GroupModuleId;
import co.id.hci.app.model.Module;
import co.id.hci.app.model.User;
import co.id.hci.app.service.UserService;

/**
 * @author rosaekapratama@gmail.com
 *
 */
@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;

	private User getRosa() {
		Group userD = new Group();
		userD.setId(4L);
		userD.setName("UserD");
		userD.setGroupModules(new ArrayList<GroupModule>());
		
		User rosa = new User();
		rosa.setUsername("rosa");
		rosa.setDate(new Date());
		rosa.setGroup(userD);
		return rosa;
	}

	private User getEka() {
		User eka = new User();
		eka.setUsername("eka");
		eka.setDate(new Date());
		return eka;
	}
	
	private User getTamama() {
		Group userA = new Group();
		userA.setId(1L);
		userA.setName("UserA");
		
		co.id.hci.app.model.Module promoCard = new Module();
		promoCard.setId(1L);
		promoCard.setName("PromoCard");
		co.id.hci.app.model.Module categoryCard = new Module();
		categoryCard.setId(2L);
		categoryCard.setName("CategoryCard");
		co.id.hci.app.model.Module flashSaleCard = new Module();
		flashSaleCard.setId(3L);
		flashSaleCard.setName("FlashSaleCard");
		co.id.hci.app.model.Module historyCard = new Module();
		historyCard.setId(4L);
		historyCard.setName("HistoryCard");
		co.id.hci.app.model.Module newsCard = new Module();
		newsCard.setId(5L);
		newsCard.setName("NewsCard");
		
		GroupModule groupModule1 = new GroupModule(new GroupModuleId(1L, 1L), userA, promoCard, 1);
		GroupModule groupModule2 = new GroupModule(new GroupModuleId(1L, 2L), userA, categoryCard, 2);
		GroupModule groupModule3 = new GroupModule(new GroupModuleId(1L, 3L), userA, flashSaleCard, 3);
		GroupModule groupModule4 = new GroupModule(new GroupModuleId(1L, 4L), userA, historyCard, 4);
		GroupModule groupModule5 = new GroupModule(new GroupModuleId(1L, 5L), userA, newsCard, 5);
		
		List<GroupModule> list = new ArrayList<GroupModule>();
		list.add(groupModule1);
		list.add(groupModule2);
		list.add(groupModule3);
		list.add(groupModule4);
		list.add(groupModule5);
		
		userA.setGroupModules(list);
		
		User tamama = new User();
		tamama.setUsername("tamama");
		tamama.setDate(new Date());
		tamama.setGroup(userA);
		return tamama;
	}
	
	@BeforeEach
	public void setup() {
		User tamama = getTamama();
		User eka = getEka();
		User rosa = getRosa();
		Mockito.when(userService.findById(tamama.getUsername())).thenReturn(tamama);
		Mockito.when(userService.findById(eka.getUsername())).thenReturn(eka);
		Mockito.when(userService.findById(rosa.getUsername())).thenReturn(rosa);
	}

	@Test
    public void usernameFound() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/u/{username}/modules", "tamama")
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		
		MockHttpServletResponse httpResponse = mvcResult.getResponse();
		assertThat(httpResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
		String expectedResponseBody = "{ \r\n" + 
				"\"modules\":[ { \r\n" + 
				"\"moduleName\":\"PromoCard\", \"moduleOrder\":1 \r\n" + 
				"}, { \r\n" + 
				"\"moduleName\":\"CategoryCard\", \"moduleOrder\":2 \r\n" + 
				"}, { \r\n" + 
				"\"moduleName\":\"FlashSaleCard\", \"moduleOrder\":3 \r\n" + 
				"}, { \r\n" + 
				"\"moduleName\":\"HistoryCard\", \"moduleOrder\":4 \r\n" + 
				"}, { \r\n" + 
				"\"moduleName\":\"NewsCard\", \"moduleOrder\":5 \r\n" + 
				"} \r\n" + 
				"] \r\n" + 
				"} ";
		Group expectedGroup = objectMapper.readValue(expectedResponseBody, Group.class);
		Group actualGroup = objectMapper.readValue(httpResponse.getContentAsString(), Group.class);
		assertThat(expectedGroup).isEqualTo(actualGroup);
    }

	@Test
    public void usernameNotFound() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/u/{username}/modules", "rosaekapratama")
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		
		MockHttpServletResponse httpResponse = mvcResult.getResponse();
		assertThat(httpResponse.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
    public void groupNotFound() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/u/{username}/modules", "eka")
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		
		MockHttpServletResponse httpResponse = mvcResult.getResponse();
		assertThat(httpResponse.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
    public void moduleEmpty() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/u/{username}/modules", "rosa")
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		
		MockHttpServletResponse httpResponse = mvcResult.getResponse();
		assertThat(httpResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
		Group actualGroup = objectMapper.readValue(httpResponse.getContentAsString(), Group.class);
		assertThat(actualGroup.getGroupModules().isEmpty()).isTrue();
	}
}
