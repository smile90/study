package org.duancq90.study.drools;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.kie.api.KieServices;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;

/**
 * @author dcq
 * @version V1.0
 * @date 2021/7/7 上午11:56
 */
public class DroolsUtils {

    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PRIVATE)
    private KieSession kieSession = null;

    public DroolsUtils() {
    }

    public DroolsUtils createKieSessionByString(String content) {
        KieHelper helper = new KieHelper();
        helper.addContent(content, ResourceType.DRL);
        kieSession = helper.build().newKieSession();
        return this;
    }

    public DroolsUtils createKieSessionByFile(String kSessionName) {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer container = kieServices.getKieClasspathContainer();

        setKieSession(container.newKieSession(kSessionName));
        return this;
    }

    public DroolsUtils insert(Object object) {
        getKieSession().insert(object);
        return this;
    }

    public DroolsUtils fireAllRules() {
        getKieSession().fireAllRules();
        return this;
    }

    public DroolsUtils dispose() {
        getKieSession().dispose();
        return this;
    }
}
