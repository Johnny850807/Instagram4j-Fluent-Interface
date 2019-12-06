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


import org.brunocvcunha.instagram4j.requests.InstagramApprovePendingThreadRequest;
import org.brunocvcunha.instagram4j.requests.InstagramDeclinePendingThreadRequest;
import tw.waterball.api.InstagramInbox;
import tw.waterball.api.InstagramInboxThread;
import tw.waterball.api.InstagramPendingInboxThread;
import org.brunocvcunha.instagram4j.requests.InstagramGetPendingInboxRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramInboxResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramPendingInboxResult;
import tw.waterball.api.InstagramSession;

import java.util.List;
import java.util.stream.Collectors;

public class Instagram4JInboxAdapter implements InstagramInbox {
    private transient Instagram4JAdapter ig;
    private InstagramInboxResult getInboxResult;
    private InstagramPendingInboxResult getPendingInboxResult;

    Instagram4JInboxAdapter(Instagram4JAdapter ig, InstagramInboxResult getInboxResult) {
        this.ig = ig;
        this.getInboxResult = getInboxResult;
    }

    @Override
    public boolean hasUnseen() {
        return getInboxResult.getInbox().unseen_count > 0;
    }

    @Override
    public boolean hasPendingRequest() {
        return getInboxResult.pending_requests_total > 0;
    }

    @Override
    public List<InstagramInboxThread> getRecentThreads() {
        return AdapterWrapping.wrap4JInboxThreads(ig, getInboxResult.getInbox().getThreads());
    }

    @Override
    public List<InstagramPendingInboxThread> getRecentPendingThreads() {
        if (getPendingInboxResult == null)
            getPendingInboxResult = ig.sendRequest(new InstagramGetPendingInboxRequest());
        return AdapterWrapping.wrap4JPendingInboxThreads(ig, getPendingInboxResult.inbox.getThreads());
    }

    @Override
    public InstagramInbox approvePendingThread(String threadId) {
        ig.sendRequest(new InstagramApprovePendingThreadRequest(threadId));
        return this;
    }

    @Override
    public InstagramInbox declinePendingThread(String threadId) {
        ig.sendRequest(new InstagramDeclinePendingThreadRequest(threadId));
        return this;
    }

}
