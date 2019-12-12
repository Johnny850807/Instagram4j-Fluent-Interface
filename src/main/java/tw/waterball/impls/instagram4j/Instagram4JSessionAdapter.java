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

import org.brunocvcunha.instagram4j.requests.*;
import tw.waterball.api.*;

import java.util.List;

public class Instagram4JSessionAdapter implements InstagramSession {
    private Instagram4JAdapter ig;

    Instagram4JSessionAdapter(Instagram4JAdapter ig) {
        this.ig = ig;
    }

    @Override
    public String getUsername() {
        return ig.getUsername();
    }

    @Override
    public String getUserId() {
        return ig.getUserId();
    }

    @Override
    public InstagramUser searchUser(String username) {
        return new Instagram4JUserAdapter(ig,
                ig.sendRequest(new InstagramSearchUsernameRequest(username)).getUser());
    }

    @Override
    public List<InstagramComment> searchCommentsUnderFeed(long feedPk) {
        return AdapterWrapping.wrap4JComments(ig, ig.sendRequest(new InstagramGetMediaCommentsRequest(
                String.valueOf(feedPk), null)).getComments());
    }

    @Override
    public InstagramSession comment(long mediaId, String message) {
        ig.sendRequest(new InstagramPostCommentRequest(mediaId, message));
        return this;
    }

    @Override
    public InstagramSession like(long mediaId) {
        ig.sendRequest(new InstagramLikeRequest(mediaId));
        return this;
    }

    @Override
    public List<InstagramFeed> searchFeedsByTag(String tag) {
        return AdapterWrapping.wrap4JFeedItems(ig,
                ig.sendRequest(new InstagramTagFeedRequest(tag)).getItems());
    }

    @Override
    public InstagramInbox inbox() {
        return new Instagram4JInboxAdapter(ig, ig.sendRequest(new InstagramGetInboxRequest()));
    }



}
