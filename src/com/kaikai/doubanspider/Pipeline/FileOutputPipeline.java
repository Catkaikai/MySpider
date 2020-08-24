package com.kaikai.doubanspider.Pipeline;

import java.util.Map.Entry;

import com.kaikai.doubanspider.output.FileOutput;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
/** 
* @author 作者 kaikai: 
* @version 创建时间：2020年8月24日 下午6:16:05 
* @Description 类说明 将数据输出到文件
*/
public class FileOutputPipeline implements Pipeline {

	@Override
	public void process(ResultItems resultItems, Task task) {
		for (Entry<String, Object> entry : resultItems.getAll().entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if(key=="帖子标题") {
				FileOutput.write("####"+value+"\n");
			}else if(key=="帖子副标题") {			
					FileOutput.write(">"+value+"\n");
			}else if(key=="发布时间") {
				FileOutput.write("- "+value+"\n");
			}else if(key=="发布者") {
				FileOutput.write("- [发布者]"+""+(value)+""+"\n");
			}else if(key=="帖子链接") {
				FileOutput.write("- [帖子链接]"+""+(value)+""+"\n");
				FileOutput.write(" "+"\n");
			}else {
				FileOutput.write(value+"\n");
			}
        }
		FileOutput.write("\n"+"------"+"\n");
	}

}
