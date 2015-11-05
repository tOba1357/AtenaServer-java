package security;

import org.apache.commons.lang3.StringUtils;
import play.cache.Cache;
import play.data.Form;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

/**
 * @author Tatsuya Oba
 */
public class AuthenticatorUser extends Security.Authenticator {
    @Override
    public String getUsername(final Http.Context context) {
        final String sessionId = Form.form().bindFromRequest().get("sessionId");
        if (StringUtils.isBlank(sessionId)) {
            return null;
        }
        final String userId = (String) Cache.get(sessionId);

        return StringUtils.isBlank(userId) ? null : userId;
    }

    @Override
    public Result onUnauthorized(final Http.Context context) {
        return badRequest(Json.toJson("sessionIdが無効または未入力です。"));
    }
}
