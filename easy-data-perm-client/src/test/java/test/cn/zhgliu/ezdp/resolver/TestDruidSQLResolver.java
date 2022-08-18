package test.cn.zhgliu.ezdp.resolver;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.zhgliu.ezdp.finder.impl.http.HttpDataPermRuleFinder;
import cn.zhgliu.ezdp.model.DataPermissionItem;
import cn.zhgliu.ezdp.resolver.impl.ali.druid.DruidSQLResolver;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class TestDruidSQLResolver {

    private static final String SQL_WITH_ALIAS = "select * from data_table a where a.original_field = 3";
    private static final String SQL_WITHOUT_ALIAS = "select * from data_table where original_field = 3";


    @Test
    public void resolveSqlWithGroupRuleTest() throws FileNotFoundException {
        ClassPathResource resource = new ClassPathResource("test-rule.json");
        String read = IoUtil.read(new FileReader(resource.getFile()));
        System.out.println(read);
        List<List<DataPermissionItem>> rules = HttpDataPermRuleFinder.stringToList(read);

        DruidSQLResolver resolver = new DruidSQLResolver();
        String result = resolver.resolveSqlWithGroupRule(SQL_WITH_ALIAS, rules);
        System.out.println(result);
        result = resolver.resolveSqlWithGroupRule(SQL_WITHOUT_ALIAS, rules);
        System.out.println(result);


    }
}
