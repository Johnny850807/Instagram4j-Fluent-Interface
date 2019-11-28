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

import tw.waterball.api.InstagramInboxThread;
import tw.waterball.api.InstagramPendingInboxThread;
import org.brunocvcunha.instagram4j.requests.InstagramApprovePendingThreadRequest;
import org.brunocvcunha.instagram4j.requests.InstagramDeclinePendingThreadRequest;

public class Instagram4JPendingInboxThreadAdapter extends Instagram4JInboxThreadAdapter implements InstagramPendingInboxThread {
    Instagram4JPendingInboxThreadAdapter(Instagram4JAdapter ig, org.brunocvcunha.instagram4j.requests.payload.InstagramInboxThread thread) {
        super(ig, thread);
    }

    @Override
    public InstagramInboxThread approve() {
        ig.sendRequest(new InstagramApprovePendingThreadRequest(getId()));
        return this;
    }

    @Override
    public InstagramInboxThread decline() {
        ig.sendRequest(new InstagramDeclinePendingThreadRequest(getId()));
        return this;
    }
}
