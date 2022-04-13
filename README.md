# CloudPlatform  
需求定义 
东软智能制造云平台，以“工业 4.0”和“互联网+”理念为框架，智能数控设备为基础，打造面向制造业全产业链开放的工业互联网平台，形成企业间的设计、供应、制造和服务等机加工生产全环节并行组织和协同能力，打造社会化协同的云制造服务平台，为企业和个人客户群体提供一站式的云制造服务。云平台系统建成后，可满足：
1．智能化计划排产
2．数字化质量检验
3．无纸化生产过成控制
4．全程质量追溯与分析
5．设计工艺数据统一管理与发放。
在具体实施过程中的整体要求为：
1. 要求使用MVC（Model-View-Controller）的设计模式，控制器和模型层采用单例模式。
2. 设计合理的实体关系，并使用UML类图描述出来，如工厂所有者可注册工厂，在工厂内分配员工，工厂与员工为一对多关系。管理员可创建设备，设备可被多个工厂租用，设备与工厂为一对多关系。 
3. 用户操作产生的数据以json格式，或者序列化对象的方式存储，要求使用泛型（类型参数化）的方式封装这类文件存取功能，形成文件存取的工具类。
4. 对用户、工厂、设备等信息保存在文件中，使用字符流读取。
5. 界面美观，表格具有适当的行高和列宽，数据量大时以滚动窗口显示。
 
 系统设计
    云平台系统的整体功能构架为：注册、登录、首页、产品管理、用户管理、订单管理、产能中心、生产调度管理、生产跟踪。
云平台系统面向三种不同对象，可提供不同的功能权限。用户根据提示及要求完成注册并登录进入系统，身份可分为超级管理员、云工厂管理员、经销商，以不同的身份经系统识别后进入系统，呈现出不同的主界面，三种身份，分别对应的功能权限。
 <img width="535" alt="1" src="https://user-images.githubusercontent.com/62821148/163172741-a93f6474-d9bf-44f6-bbb5-a4a40569ceeb.png">
<img width="907" alt="2" src="https://user-images.githubusercontent.com/62821148/163172750-3c80be93-d6c1-4616-b19c-b08ec7230cfb.png">
<img width="735" alt="3" src="https://user-images.githubusercontent.com/62821148/163172754-cedc96cb-dfb7-415d-bf90-c5efef8e3e52.png">
<img width="739" alt="4" src="https://user-images.githubusercontent.com/62821148/163172761-bf74d6d2-4429-411d-9f6b-b33a60fef20b.png">
<img width="908" alt="5" src="https://user-images.githubusercontent.com/62821148/163172765-6b1d46dc-0165-4e58-a693-a9aff3d448e2.png">
<img width="603" alt="6" src="https://user-images.githubusercontent.com/62821148/163172769-ca52b223-6669-43f1-bb5a-4c7d56e61cc8.png">
<img width="906" alt="7" src="https://user-images.githubusercontent.com/62821148/163172773-093c8f0e-620d-402e-96b3-c072eb4d72ca.png">
<img width="906" alt="8" src="https://user-images.githubusercontent.com/62821148/163172779-341d0aa5-bbca-48d0-831b-5b19b119121e.png">
<img width="909" alt="9" src="https://user-images.githubusercontent.com/62821148/163172782-c7edddb1-ba15-4b0f-8ce6-2e5cc375bbb8.png">
<img width="904" alt="10" src="https://user-images.githubusercontent.com/62821148/163172789-c6078ec9-9d60-4604-a3ba-511ba7e23c65.png">
<img width="524" alt="11" src="https://user-images.githubusercontent.com/62821148/163172793-5ef02fae-f67e-49c8-9317-a3f772e7c0e8.png">
<img width="670" alt="12" src="https://user-images.githubusercontent.com/62821148/163172801-38c7bb84-16fe-4fde-8989-d14c3bf6d37e.png">
<img width="533" alt="13" src="https://user-images.githubusercontent.com/62821148/163172806-7927b5ac-32dc-43ca-bb7c-b9024fdd8b29.png">
