package org.duancq90.study.drools;

import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dcq
 * @version V1.0
 * @date 2021/7/7 上午11:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class RuleTest {

    @Test
    public void testAgeByFile() {
        Person person = new Person();
        person.setName("test");
        person.setAge(20);
        person.setGender(Gender.MAN);

        RuleObject<String, Object> ruleObject = new RuleObject<>();
        ruleObject.put("one", BigDecimal.valueOf(0.935D));
        ruleObject.put("two", BigDecimal.valueOf(0.1314D));

        new DroolsUtils()
                .createKieSessionByFile("all-rules")
                .insert(person)
                .insert(ruleObject)
                .fireAllRules()
                .dispose();

        System.out.println(ruleObject);
    }

    @Test
    public void testAgeByString() {
        Person person = new Person();
        person.setName("test");
        person.setAge(15);
        person.setGender(Gender.MAN);

        String content = "import org.duancq90.study.drools.Person\n" +
                "\n" +
                "dialect  \"mvel\"\n" +
                "\n" +
                "rule \"age\"\n" +
                "    when\n" +
                "        $person : Person(age < 18 || age > 60)\n" +
                "    then\n" +
                "        System.out.println($person.name + \"的年龄不符合要求!\");\n" +
                "end\n";

        new DroolsUtils()
                .createKieSessionByString(content)
                .insert(person)
                .fireAllRules()
                .dispose();
    }
}
