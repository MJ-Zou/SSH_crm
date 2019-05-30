package action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import domain.Dict;
import org.apache.struts2.ServletActionContext;
import service.DictService;
import utils.FastJsonUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 字典控制器
 */
public class DictAction extends ActionSupport implements ModelDriven<Dict> {
    private Dict dict = new Dict();

    @Override
    public Dict getModel() {
        return dict;
    }

    private DictService dictService;

    public void setDictService(DictService dictService) {
        this.dictService = dictService;
    }

    /**
     * 通过字段的type_code查询客户级别或者客户的来源
     */
    public String findByCode() {
        List<Dict> list=dictService.findByCode(dict.getDict_type_code());
        //List<Dict> list=dictService.findByCode("006");
        //fastjson装换成字符串
        String jsonString = FastJsonUtil.toJSONString(list);
        //json写回到浏览器
        HttpServletResponse response = ServletActionContext.getResponse();
        //输出
        FastJsonUtil.write_json(response,jsonString);
        return NONE;
    }


}
