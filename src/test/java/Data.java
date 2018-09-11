import cn.bidlink.openapi.client.commons.HttpMethod;
import cn.bidlink.openapi.client.commons.OpenApiUri;
import cn.bidlink.openapi.dto.RegisterResult;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2017/2/10 11:01$
 */
public class Data {

    String[] defaultmediaTypes = {"application/json"};

    public void  test(){
        //POST_VALID_VERIFY_COMPANY
        OpenApiUri openApiUri =new OpenApiUri(
                HttpMethod.POST, "center", "",
                "/usercenter/register/validVerifyCompany",
                RegisterResult.class, null, "");
    }

}
