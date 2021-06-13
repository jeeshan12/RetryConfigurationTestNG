import org.testng.IConfigurable;
import org.testng.IConfigureCallBack;
import org.testng.ITestResult;

import java.util.Objects;

public class Configuration implements IConfigurable {
    @Override
    public void run(IConfigureCallBack callBack, ITestResult testResult) {
        Retry retry = testResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Retry.class);
        int attempts = 1;

        if (Objects.nonNull(retry))  {
            attempts = retry.retries();
        };
        for (int i = 0; i <= attempts ; i++) {
                callBack.runConfigurationMethod(testResult);
                if (testResult.getThrowable() == null) {
                    break;
                }
        }

    }
}
