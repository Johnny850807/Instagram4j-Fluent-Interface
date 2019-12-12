/*
 * Copyright 2019 Johnny850807
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

/*
 * Copyright 2019 Johnny850807
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

/*
 * Copyright 2019 Johnny850807
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package tw.waterball.impls.instagram4j;

import org.apache.http.StatusLine;
import tw.waterball.api.Instagram;
import tw.waterball.api.InstagramSession;
import tw.waterball.api.InstagramUser;
import tw.waterball.exceptions.InstagramException;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramRequest;

import java.io.IOException;

public class Instagram4JAdapter implements Instagram {
    private transient Instagram4j instagram4j;

    public Instagram4JAdapter() { }

    public Instagram4JAdapter(Instagram4j instagram4j) {
        this.instagram4j = instagram4j;
    }

    @Override
    public InstagramSession login(String username, String password) {
        if (instagram4j == null)
            instagram4j = Instagram4j.builder().username(username)
                    .password(password).build();
        setupAndLoginInstagram4J();
        return new Instagram4JSessionAdapter(this);
    }

    private void setupAndLoginInstagram4J() {
        try {
            instagram4j.setup();
            instagram4j.login();

            StatusLine statusLine = instagram4j.getLastResponse().getStatusLine();

            if (statusLine.getStatusCode() != 200)
                throw new InstagramException(statusLine.getReasonPhrase(), statusLine.getStatusCode());
        } catch (IOException err) {
            throw new InstagramException(err);
        }
    }

    public <T> T sendRequest(InstagramRequest<T> request) {
        if (instagram4j == null)
            throw new IllegalStateException("You have not logged in, cannot send requests.");

        try {
            return instagram4j.sendRequest(request);
        } catch (IOException | NullPointerException err) {
            throw new InstagramException(err);
        }
    }


    public String getUsername() {
        return instagram4j.getUsername();
    }

    public String getUserId() {
        return String.valueOf(instagram4j.getUserId());
    }
}
