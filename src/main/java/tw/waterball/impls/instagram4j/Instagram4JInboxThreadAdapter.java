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


import org.brunocvcunha.instagram4j.requests.InstagramDirectShareRequest;
import org.brunocvcunha.instagram4j.requests.InstagramGetInboxThreadRequest;
import tw.waterball.api.AbstractInstagramId;
import tw.waterball.api.InstagramInboxThread;
import tw.waterball.api.InstagramInboxThreadMessage;
import tw.waterball.api.InstagramUser;
import tw.waterball.api.pagination.Pagination;
import tw.waterball.impls.instagram4j.pagination.InstagramInboxThreadMessagePagingIterator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("WeakerAccess")
public class Instagram4JInboxThreadAdapter extends AbstractInstagramId implements InstagramInboxThread {
    protected transient Instagram4JAdapter ig;
    protected List<InstagramUser> users;
    protected org.brunocvcunha.instagram4j.requests.payload.InstagramInboxThread thread;

    public Instagram4JInboxThreadAdapter(Instagram4JAdapter ig, org.brunocvcunha.instagram4j.requests.payload.InstagramInboxThread thread) {
        super(thread.getThread_id());
        this.ig = ig;
        this.thread = thread;
        this.users = AdapterWrapping.wrap4JUser(ig, thread.getUsers());
    }

    @Override
    public List<InstagramInboxThreadMessage> getRecentMessages() {
        return AdapterWrapping.wrap4JInboxThreadMessage(
                        ig.sendRequest(new InstagramGetInboxThreadRequest(getId(), null))
                        .getThread().getItems());
    }

    @Override
    public Pagination<InstagramInboxThreadMessage> getMessages(int maxNum) {
        return new Pagination<>(new InstagramInboxThreadMessagePagingIterator(maxNum, ig, getId()));
    }


    @Override
    public InstagramInboxThread reply(String message) {
        ig.sendRequest(InstagramDirectShareRequest.builder()
                .shareType(InstagramDirectShareRequest.ShareType.MESSAGE)
                .threadId(getId())
                .message(message).build());
        return this;
    }

    @Override
    public List<InstagramUser> getUsers() {
        return users;
    }
}

