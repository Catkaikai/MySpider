#### MySpider

##### 知识点 

`类加载`  `I/O流`  `配置文件读取`

##### 前言

​		应届毕业生想在异地找个工作，但是又不太会租房子。听别人说某平台讨论组里有有好多优质房源，但排版不好看且不利于检索。于是灵机一动，何不自己整一个？于是就有了MySpider。

##### 如何使用

1. 下载最新版:arrow_down:

2. 按照压缩包里的提示填写`config`目录下的`config.properties`的内容:pencil2:

3. 在本机已`配置Java环境`的情况下，直接在jar包所在目录运行:runner:

   ```
   java -jar ./myspider.jar
   ```

4. output文件夹下即会生成`output.md`以及`日志`:happy:

##### 优势

- `开源`  `安全`  `透明`
- `无安装包，解压即可用`
- `基于优秀的国产依赖`[webmagic](https://github.com/code4craft/webmagic)

##### To Do

- 提供可视化UI
- 云端部署 持久运行
- 自动部署
- 数据预筛选