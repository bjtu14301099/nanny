package com.bjtu.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.bjtu.service.NannyUserService;
import com.bjtu.service.ResourcesService;

import net.sf.json.JSONObject;

/**
 * �û���Ϣ������
 */
@Controller
public class UserInfoController {

	@Autowired
	private ResourcesService resourcesService;

	@Autowired
	private NannyUserService nannyUserService;

	public ResourcesService getResourcesService() {
		return resourcesService;
	}

	public void setResourcesService(ResourcesService resourcesService) {
		this.resourcesService = resourcesService;
	}

	public NannyUserService getNannyUserService() {
		return nannyUserService;
	}

	public void setNannyUserService(NannyUserService nannyUserService) {
		this.nannyUserService = nannyUserService;
	}

	// ��ȡ�û�ͼƬ
	@RequestMapping(value = "/image")
	@ResponseBody
	public String getImage(HttpServletRequest request) throws IOException {
		ArrayList<StringBuilder> list = new ArrayList<StringBuilder>();
		int count = Integer.parseInt(request.getParameter("page"));
		Map<Integer, String> result = resourcesService.getImageUrl(count);
		if (result != null) {
			Iterator iterator = result.entrySet().iterator();
			while (iterator.hasNext()) {
				StringBuilder a = new StringBuilder("{\"user_id\":");
				Map.Entry entry = (Map.Entry) iterator.next();
				int user_id = (int) entry.getKey();
				String pic_url = (String) entry.getValue();
				a.append("\"" + user_id + "\"," + "\"pic_url\":" + "\"" + pic_url + "\"");
				a.append("}");
				list.add(a);
			}
		}
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			if ((i + 1) != list.size())
				sb.append(",");
		}
		sb.append("]");
		System.out.println(sb.toString());
		return sb.toString();
	}

	@RequestMapping(value = "/imgUpload")
	@ResponseBody
	public void imgUpload(HttpServletRequest request) throws IllegalStateException, IOException {
		// HttpSession session = request.getSession();
		// String email = (String)session.getAttribute("sessionName");
		int user_id = 33;
		// if(email!=null) user_id = nannyUserService.getIdByUsername(email);
		// ����һ��CommonstMultipartResolver,��ServletContext��ʼ��
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// �������Ƿ���� enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// ��request������MultipartHttpServletRequest������ȡ�����ļ���
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator iter = multiRequest.getFileNames();

			while (iter.hasNext()) {
				// һ�α��������ļ�
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				if (file != null) {
					// ȷ���ļ�·�����ϴ�
					String t = Thread.currentThread().getContextClassLoader().getResource("").getPath();
					int num = t.indexOf(".metadata");
					String path = t.substring(1, num).replace('/', '\\') + "nanny_new\\WebContent\\imgResources\\";
					String rootpath = path.replaceAll("\\\\", "/");
					String oringinalFilename = file.getOriginalFilename();
					String[] parsePath = oringinalFilename.split("\\.");
					String resPath = "";
					if (user_id != 0)
						resPath = rootpath + user_id + "." + parsePath[1];
					else
						resPath = rootpath + "none" + "." + parsePath[1];
					file.transferTo(new File(resPath));
					// �������ݿ�
					if (user_id != 0)
						resourcesService.savePicUrl(user_id, user_id + "." + parsePath[1]);
				}

			}

		}
	}

}
