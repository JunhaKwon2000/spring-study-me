package com.winter.app.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.winter.app.board.BoardFileVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileDownView extends AbstractView {
	
	@Value("${app.upload}")
	private String path;
	

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		
		// log.info("{}", model);
		BoardFileVO boardFileVO = (BoardFileVO)model.get("file");
		log.info("========== custom view ==========");
		
		String filePath = path + model.get("board").toString(); // board 도 오고 있으니까(value로 담은 것)
		
		File file = new File(filePath, boardFileVO.getSaveName());
		
		// 1. 총 파일의 크기 - 전체 파일의 크기를 알아야 다운로드 시 남은 시간을 계산할 수 있으니까
		response.setContentLengthLong(file.length());
		
		// 2. 파일 다운 시 파일의 이름을 인코딩
		String fileName = URLEncoder.encode(boardFileVO.getOriName(), "UTF-8");
		
		// 3. header 설정 - 이 파일이 문서인지, 음악인지, 엑셀 파일인지 등등
		response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\""); // 파일 이름은 이걸로
		response.setHeader("Content-Transfer-Encoding", "binary"); // 0과 1로 파일을 전솔할 것(이진 코드)
		
		// 4. 파일을 읽기
		FileInputStream fis = new FileInputStream(file);
		
		// 5. 클라이언트와 연결
		OutputStream os = response.getOutputStream();
		
		// 6. 파일 전송
		FileCopyUtils.copy(fis, os);
		
		// 7. 자원 해지 (사용했던 순서의 역순)
		os.close();
		fis.close();
	}

}
