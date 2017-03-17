package web.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class UpLoadAction {
	@RequestMapping("/down.do")
	public void down(String fname,HttpServletRequest request,HttpServletResponse response){

		String basepath = request.getSession().getServletContext()
					.getRealPath("/upload")+"/";
		File f = new File(basepath+fname);
		try {
			InputStream is = new FileInputStream(f);
			byte[] bs = new byte[is.available()];
			is.read(bs);
			is.close();
			
			response.setContentType("application/force-download;charset=utf-8");
			response.addHeader("Content-Disposition",
					"attachment;fileName=" + fname);// 设置文件名
			OutputStream os = response.getOutputStream();
			os.write(bs);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/show.do")
	public String showlist(HttpServletRequest request){
		String basepath = request.getSession().getServletContext()
					.getRealPath("/upload")+"/";
		File dir = new File(basepath);
		if(dir.exists() && dir.isDirectory()){
			request.setAttribute("fs", dir.list());
		}
		return "/filelist.jsp";
	}
	
	@RequestMapping("/up.do")
	public String upload(
			//@RequestParam(required=false)MultipartFile ff,//单文件上传
			MultipartHttpServletRequest request,//批量上传
			String username){
		String basepath = request.getSession().getServletContext()
				.getRealPath("/upload")+"/";
		List<MultipartFile> fs = request.getFiles("ff");
		for(int i=0;i<fs.size();i++){
			try {				
				MultipartFile f = fs.get(i);
				//方法1：
//				f.transferTo(new File("d:/"+i+f.getOriginalFilename()));
				//方式2：
				System.out.println(f.getBytes().length);
				FileUtils.writeByteArrayToFile(new 
						File(basepath+(i+1)+f.getOriginalFilename()),
						f.getBytes());
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return "redirect:/index.jsp";
	}
}
