package listeners;

import io.qameta.allure.Attachment;
import io.restassured.response.Response;
import org.testng.ITestResult;

public class ApiTestListener extends BaseListener {

    @Override
    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result); // Save base info (name, status, throwable)

        Object respObj = result.getAttribute("response");
        if (respObj instanceof Response) {
            saveResponse((Response) respObj);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        super.onTestSkipped(result);
        saveSkipReason(result.getThrowable());
    }

    // ===== Allure attachments for API tests =====
    @Attachment(value = "API Response", type = "application/json")
    private String saveResponse(Response response) {
        return response == null ? "No Response captured" : response.asPrettyString();
    }

    @Attachment(value = "Skip Reason", type = "text/plain")
    private String saveSkipReason(Throwable throwable) {
        return throwable == null ? "No skip reason" : throwable.toString();
    }
}