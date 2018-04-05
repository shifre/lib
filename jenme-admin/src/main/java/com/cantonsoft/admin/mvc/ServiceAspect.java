package com.cantonsoft.admin.mvc;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LogFactory;
import org.slf4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.cantonsoft.admin.account.auth.SessionStorage;
import com.cantonsoft.admin.account.auth.ShiroSessionHelper;
import com.cantonsoft.core.account.user.model.GenericUser;
import com.cantonsoft.framework.mvc.util.ApplicationUtil;

//@Component
//@Aspect
public class ServiceAspect {
	private static final Logger LOG = LogFactory.getLogger(ServiceAspect.class);


	
	public static <T> T getBean(Class<T> clazz) {
		return ApplicationUtil.WEBAPP_CONTEXT.getBean(clazz);
	}
	
	public static String getUserTitle() {
		String title="";
		
		WebApplicationContext context= ApplicationUtil.WEBAPP_CONTEXT;
		if(context!=null){
			ShiroSessionHelper helper=context.getBean(ShiroSessionHelper.class);
			if(helper!=null){
				SessionStorage  session= helper.getSession();
				if(session!=null){
					GenericUser  user=session.getUser();
					if(user!=null){
						 title=user.getTitle();
					}
				}
			}
		}
			
		return title;	
        
	}
	
    //所有的api和controller，排除LogApi和mongoapi
	@Pointcut("(execution(public * com.cantonsoft..*Api.*(..)) || execution(public * com.cantonsoft..*Controller.*(..))) &&!execution(public * com.cantonsoft.admin.logs.LogApi.*(..))&&!execution(public * com.cantonsoft.framework.mvc.service.MongoCrudApi.*(..))")
	public void aspect() {
	}
	
	@Before("aspect()")
	public void before(JoinPoint joinPoint) {
		if (LOG.isInfoEnabled()) {
			LOG.info("before " + joinPoint);
		}
		writeLog(joinPoint, null);
	}
	
	
	@AfterReturning(pointcut = "aspect()", returning = "returnVal")
	public void afterReturning(JoinPoint joinPoint, Object returnVal)
	{
		System.out.println("afterReturning=" + joinPoint + ", return result is " + returnVal);
		writeLog(joinPoint, returnVal);
	}

	@AfterThrowing(pointcut = "aspect()", throwing = "ex")
	public void afterThrow(JoinPoint joinPoint, Exception ex)
	{
		System.out.println("afterThrow" + joinPoint);
		ex.printStackTrace();
		writeLog(joinPoint, ex.getMessage());
	}
	
	





//	@Around("aspect()")
//	public Object around(JoinPoint joinPoint) throws Throwable{
//		long start = System.currentTimeMillis();
//		try {
//			writeLog(joinPoint, null);
//			Object proceed = ((ProceedingJoinPoint) joinPoint).proceed();
//			writeLog(joinPoint, proceed);
//			long end = System.currentTimeMillis();
//			if (LOG.isInfoEnabled()) {
//				LOG.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
//			}
//			return proceed;
//		} catch (Throwable e) {
//			long end = System.currentTimeMillis();
//			if (LOG.isInfoEnabled()) {
//				LOG.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
//			}
//			e.printStackTrace();
//			throw e;
//		}
//	}

//	@AfterReturning("aspect()")
//	public void afterReturn(JoinPoint joinPoint) {
//		// 判断参数
//		if (joinPoint.getArgs() == null) {// 没有参数
//			return;
//		}
//		if (LOG.isInfoEnabled()) {
//			LOG.info("afterReturn " + joinPoint);
//		}
//	}
	
	
	

	private void writeLog(JoinPoint joinPoint, Object retObj)   {
		// 获取方法名
		String methodName = getMethodName(joinPoint);

		// 获取操作内容
		String opContent = null;
		try {
			if(retObj==null){
				opContent = adminOptionContent(joinPoint.getArgs(), methodName);
			}else{
				opContent = retObj == null ? "" : retObj.toString();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}



	private String getMethodName(JoinPoint joinPoint) {

		String signature = joinPoint.getSignature().toShortString();
		
		
        /*
		if (StringUtils.contains(signature, "ParkLoginExt")) {
			return "登陆-Login";
		}
		if (StringUtils.contains(signature, "ParkSyncExt")) {
			return "心跳-Sync";
		}
		if (StringUtils.contains(signature, "CheckInExt")) {
			return "进场-Checkin";
		}
		if (StringUtils.contains(signature, "CheckOutExt")) {
			return "出场-Checkout";
		}
		if (StringUtils.contains(signature, "PaymentExt")) {
			return "支付-Payment";
		}
		if (StringUtils.contains(signature, "PaymentAckExt")) {
			return "支付回复-PaymentAck";
		}
		if (StringUtils.contains(signature, "LogoutExt")) {
			return "登出-Logout";
		}
		if (StringUtils.contains(signature, "WxPaymentExt")) {
			return "微信-askPay";
		}
		if (StringUtils.contains(signature, "WxParkExt")) {
			return "微信-Park";
		}
		if (StringUtils.contains(signature, "WxNoticeExt")) {
			return "微信-Notice";
		}*/

		return signature;
	}

	
	
	public String getToken(Object[] args) throws Exception {
		if (args == null) {
			return null;
		}

		String className = null;
		// 遍历参数对象
		for (Object info : args) {

			// 获取对象类型
			className = info.getClass().getName();
			className = className.substring(className.lastIndexOf(".") + 1);
			

			// 获取对象的所有方法
			Method[] methods = info.getClass().getDeclaredMethods();

			// 遍历方法，判断get方法
			for (Method method : methods) {

				String methodName = method.getName();
				// 判断是不是get方法
				if (methodName.indexOf("get") == -1) {// 不是get方法
					continue;// 不处理
				}

				Object rsValue = null;
				try {

					// 调用get方法，获取返回值
					rsValue = method.invoke(info);

					if (rsValue == null) {// 没有返回值
						continue;
					}

				} catch (Exception e) {
					continue;
				}

				if("token".equals(methodName)){
					return rsValue==null?"":rsValue.toString();
				}
			}

		}

		return "";
	}

	public String adminOptionContent(Object[] args, String mName) throws Exception {
		if (args == null) {
			return null;
		}

		StringBuffer rs = new StringBuffer();
		rs.append(mName);
		String className = null;
		int index = 1;
		// 遍历参数对象
		for (Object info : args) {
			if (info == null)
			{
				break;
			}

			// 获取对象类型
			className = info.getClass().getName();
			className = className.substring(className.lastIndexOf(".") + 1);
			if("ResponseFacade".equals(className)||"ShiroHttpServletRequest".equals(className)){
				continue;
			}
			rs.append("[参数" + index + "，类型：" + className + "，值：");
			if("String".equals(className)){
				rs.append(info+"]");
				index++;
				continue;
			}

			// 获取对象的所有方法
			Method[] methods = info.getClass().getDeclaredMethods();

			// 遍历方法，判断get方法
			for (Method method : methods) {

				String methodName = method.getName();
				// 判断是不是get方法
				if (methodName.indexOf("get") == -1) {// 不是get方法
					continue;// 不处理
				}

				Object rsValue = null;
				try {

					// 调用get方法，获取返回值
					rsValue = method.invoke(info);

					if (rsValue == null) {// 没有返回值
						continue;
					}

				} catch (Exception e) {
					continue;
				}

				// 将值加入内容中
				rs.append("(" + methodName + " : " + rsValue + ")");
			}

			rs.append("]");

			index++;
		}

		return rs.toString();
	}

}
