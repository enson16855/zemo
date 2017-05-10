package com.zeven.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/zemo")
public class ZemoController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	/**
	 * 上传图片
	 * @param file
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadImg", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String upload(@RequestParam(value = "file", required = true) MultipartFile file) {
		System.out.println(file.getContentType());
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());

		try {
			BufferedImage image = ImageIO.read(file.getInputStream());
			if (image != null) {//如果image=null  表示上传的不是图片格式
				System.out.println(image.getWidth());//获取图片宽度，单位px
				System.out.println(image.getHeight());//获取图片高度，单位px
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "hello";
	}
}
