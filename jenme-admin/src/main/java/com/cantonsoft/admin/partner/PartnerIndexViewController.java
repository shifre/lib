package com.cantonsoft.admin.partner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cantonsoft.core.common.notice.NoticeService;

@Controller
@RequestMapping(value = "/partner")
public class PartnerIndexViewController {
	@Autowired
	NoticeService noticeService;
	
	@RequestMapping(value = {"index.html", "dashboard.html"})
	public String gotoView(Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAttribute("noticeList",noticeService.getPatnerNoticeList());
		return "partner/dashboard";
	}
}
