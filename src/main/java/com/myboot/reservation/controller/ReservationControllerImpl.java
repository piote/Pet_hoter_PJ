package com.myboot.reservation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myboot.reservation.service.ReservationService;
import com.myboot.reservation.vo.PetserviceVO;
import com.myboot.reservation.vo.ReservationVO;
import com.myboot.user.vo.UserVO;

import oracle.sql.ARRAY;

@Controller("reservationController")
public  class ReservationControllerImpl implements ReservationController{

	@Autowired
	private ReservationService resService;
	@Autowired
	private ReservationVO resVO;
	@Autowired
	private PetserviceVO petVO;
	
//	@RequestMapping("/reservation.do") 
//		public String ReservationMain(Model model){
// 		
//		return "reservationMain"; 
//    	}
	@Override
	@RequestMapping(value= "/reservationForm.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView reservationMain(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		return mav;
		
	}
	@Override
	@RequestMapping(value= "/reservationComplete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView reservationComplete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		return mav;
		
	}
	
	@ResponseBody
	@RequestMapping("/resList.do") 
	public List resListGet(Model model){
		List ResList = null;
		try {
			ResList = resService.listReservation();
		} catch (Exception e) {

			e.printStackTrace();
		}
			
		return ResList;
	}
	
	@SuppressWarnings("null")
	@ResponseBody
	@RequestMapping(value= "/reservationAdd.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String reservationAdd( HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("user");
		String checkinDate = (String) request.getParameter("checkinDate");
		String checkoutDate = (String) request.getParameter("checkoutDate");
		String petcomment = (String) request.getParameter("petcomment");
		String costResult = (String) request.getParameter("totalcost");
		
		//petserviceTB
		String[] petName = request.getParameterValues("petname");
		String[] petGender = request.getParameterValues("petsex");
		String[] petRoom = request.getParameterValues("petroom");
		String[] petBeauty = request.getParameterValues("beauty");
		
		String[] petSpa = request.getParameterValues("spa");
		
		System.out.println(petBeauty.length);
		System.out.println(petSpa.length);
		
		
		Map petServiceMap = new HashMap();
		
		int listIndex = 0;
		for(int i=0;i<petName.length;i++) {
			if(petName[i] != null && petName[i] != "") {
				List<String> petServiceList = new ArrayList<String>();
				petServiceList.add(petName[i]);
				
				petServiceList.add(petGender[i]);
						
				petServiceList.add(petRoom[i]);
				
				petServiceList.add(petBeauty[i]);
				
				petServiceList.add(petSpa[i]);
			
				petServiceMap.put("petServiceList"+listIndex, petServiceList);
				listIndex++;
			}
		}
		
		String total = checkinDate +checkoutDate+ petcomment +userVO.getName() +"<br>"+petServiceMap;
		return total;
	}
	
	

//	@RequestMapping("/reservationcomplete.do")
//		public String ReservationComplete(Model model){
//	
//		return "reservationComplete";
//	}
	
}

