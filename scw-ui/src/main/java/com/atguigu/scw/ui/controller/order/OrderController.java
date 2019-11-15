package com.atguigu.scw.ui.controller.order;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.atguigu.scw.common.bean.ResponseVo;
import com.atguigu.scw.common.utils.AppDateUtils;
import com.atguigu.scw.ui.bean.OrderInfoSubmitVo;
import com.atguigu.scw.ui.bean.TMemberAddress;
import com.atguigu.scw.ui.bean.TOrder;
import com.atguigu.scw.ui.bean.TReturn;
import com.atguigu.scw.ui.config.AlipayConfig;
import com.atguigu.scw.ui.service.OrderControllerFeign;
import com.atguigu.scw.ui.service.ProjectInfoControllerFeign;
import com.atguigu.scw.ui.service.UserControllerFeign;
import com.atguigu.scw.ui.vo.reponse.ProjectDetailsResponseVo;
import com.atguigu.scw.ui.vo.reponse.UserResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    ProjectInfoControllerFeign projectInfoControllerFeign;

    @Autowired
    UserControllerFeign userControllerFeign;

    @Autowired
    OrderControllerFeign orderControllerFeign;

    @GetMapping("notify_url")
    public String notifyUrl(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——

	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/
        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
            orderControllerFeign.updateOrder(out_trade_no, "1");

            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }


        }else {//验证失败
            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }
        return "success";
    }

    @GetMapping("/return_url")
    public String returnUrl(HttpServletRequest request,Model model) throws AlipayApiException, UnsupportedEncodingException {
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            model.addAttribute("out_trade_no", out_trade_no);
            model.addAttribute("trade_no", trade_no);
            model.addAttribute("total_amount", total_amount);
            orderControllerFeign.updateOrder(out_trade_no, "1");
            return "order/checkout";
        }else {
            return "order/error";
        }
    }

    @ResponseBody
    @PostMapping(value = "/checkout",produces = "text/html")
    public String checkout(OrderInfoSubmitVo vo,HttpServletRequest request,HttpSession session) throws UnsupportedEncodingException{
        log.info("接收到的订单信息:{}", vo);
        TOrder order = new TOrder();
        BeanUtils.copyProperties(vo, order);
        UserResponseVo user = (UserResponseVo) session.getAttribute("responseVo");
        Integer memberId = user.getId();
        order.setMemberid(memberId);
        ProjectDetailsResponseVo projectDetails = (ProjectDetailsResponseVo) session.getAttribute("projectDetails");
        Integer projectId = projectDetails.getId();
        order.setProjectid(projectId);
        TReturn rtn = (TReturn) session.getAttribute("return");
        Integer returnId = rtn.getId();
        order.setReturnid(returnId);
        String ordernum = System.currentTimeMillis()+"_"+ UUID.randomUUID().toString().replace("-", "")+"_"+memberId;
        order.setOrdernum(ordernum);
        String createDate = AppDateUtils.getFormatTime();
        order.setCreatedate(createDate);
        Integer money = vo.getRtncount()*rtn.getSupportmoney()+rtn.getFreight();
        order.setMoney(money);
        String status = "0";
        order.setStatus(status);
        ResponseVo<String> responseVo = orderControllerFeign.createOrder(order);

        if ("200".equals(responseVo.getCode())){
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

            //设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            alipayRequest.setReturnUrl(AlipayConfig.return_url);
            alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

            //商户订单号，商户网站订单系统中唯一订单号，必填
//            String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            String out_trade_no = ordernum;
            //付款金额，必填
//            String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
            String total_amount = money + "";
            //订单名称，必填
//            String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
            String subject = projectDetails.getName();
            //商品描述，可空
//            String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
            String body = vo.getRemark();

            alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                    + "\"total_amount\":\""+ total_amount +"\","
                    + "\"subject\":\""+ subject +"\","
                    + "\"body\":\""+ body +"\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

            String result = null;
            try {
                result = alipayClient.pageExecute(alipayRequest).getBody();
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
            log.info("支付宝提交的表单内容{}", result);
            return result;
        }else {
            return "错误";
        }


    }

    @GetMapping("/toPay2")
    public String toPay2(Integer count, HttpSession session, Model model, @RequestHeader("referer") String referer) {
        UserResponseVo user = (UserResponseVo) session.getAttribute("responseVo");
        if (user == null) {
            String errorMsg = "结算前必须先登录";
            model.addAttribute("errorMsg", errorMsg);
            session.setAttribute("ref", referer);
            return "user/login";
        } else {
            String accesstoken = user.getAccesstoken();
            ResponseVo<List<TMemberAddress>> vo = userControllerFeign.getAddress(accesstoken);
            List<TMemberAddress> addresses = vo.getData();
            model.addAttribute("addresses",addresses);
            TReturn rtn = (TReturn)session.getAttribute("return");
            model.addAttribute("count",count);
            BigDecimal totalPriceBd;
            BigDecimal totalSupportMoneyBd;
            BigDecimal countBd = new BigDecimal(""+count);
            BigDecimal supportmoneyBd = new BigDecimal(""+rtn.getSupportmoney());
            BigDecimal freightBd = new BigDecimal(""+rtn.getFreight());
            totalPriceBd = countBd.multiply(supportmoneyBd).add(freightBd);
            totalSupportMoneyBd = countBd.multiply(supportmoneyBd);
            double totalSupportMoney = totalSupportMoneyBd.doubleValue();
            double totalPrice = totalPriceBd.doubleValue();
            model.addAttribute("totalSupportMoney",totalSupportMoney);
            model.addAttribute("totalPrice",totalPrice);
            return "order/pay-step-2";
        }
    }

    @GetMapping("/support")
    public String support(Integer rtnid, HttpSession session) {
        ResponseVo<TReturn> vo = projectInfoControllerFeign.getReturn(rtnid);
        session.setAttribute("return", vo.getData());
        return "order/pay-step-1";

    }
}
