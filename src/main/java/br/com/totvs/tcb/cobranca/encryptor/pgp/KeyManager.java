// Copyright 2021 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package br.com.totvs.tcb.cobranca.encryptor.pgp;

import java.io.InputStream;
import java.util.Map;
import java.util.Set;

/**
 * A key storage.
 */
public interface KeyManager {
    Map<Long, Set<String>> addPublicKeys(InputStream inputStream) throws KeyManagementException;

    Map<Long, Set<String>> addSecretKeys(InputStream inputStream, char[]... passphrases)
            throws KeyManagementException;
}
