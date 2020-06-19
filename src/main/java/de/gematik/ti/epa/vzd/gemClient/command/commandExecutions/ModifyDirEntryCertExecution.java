/*
 * Copyright (c) 2020 gematik GmbH
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.gematik.ti.epa.vzd.gemClient.command.commandExecutions;

import de.gematik.ti.epa.vzd.client.api.CertificateAdministrationApi;
import de.gematik.ti.epa.vzd.client.invoker.ApiException;
import de.gematik.ti.epa.vzd.client.invoker.ApiResponse;
import de.gematik.ti.epa.vzd.client.model.CreateDirectoryEntry;
import de.gematik.ti.epa.vzd.client.model.UserCertificate;
import de.gematik.ti.epa.vzd.gemClient.CommandNamesEnum;
import de.gematik.ti.epa.vzd.gemClient.api.GemCertificateAdministrationApi;
import de.gematik.ti.epa.vzd.gemClient.command.Transformer;
import de.gematik.ti.epa.vzd.gemClient.exceptions.CommandException;
import de.gematik.ti.epa.vzd.gemClient.invoker.GemApiClient;
import generated.CommandType;
import generated.DistinguishedNameType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Specific execution for Command "ModifyDirectoryEntryCertificate"
 */
public class ModifyDirEntryCertExecution extends ExecutionBase {

  private Logger LOG = LoggerFactory.getLogger(ModifyDirEntryExecution.class);
  private CertificateAdministrationApi certificateAdministrationApi;

  public ModifyDirEntryCertExecution(GemApiClient api) {
    super(api, CommandNamesEnum.MOD_DIR_CERT);
    certificateAdministrationApi = new GemCertificateAdministrationApi(apiClient);
  }

  @Override
  public boolean checkValidation(CommandType command) {
    if (command.getUserCertificate() != null) {
      if (command.getUserCertificate().getDn() != null) {
        DistinguishedNameType dn = command.getUserCertificate().getDn();
        if (StringUtils.isBlank(dn.getUid()) || StringUtils.isBlank(dn.getCn())) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public boolean executeCommands() {
    LOG.info("The execution for ModifyCertificate is exposed");
    return true;
//        boolean runSuccessful = true;
//        if (commands.size() == 0) {
//            return true;
//        }
//        for (CommandType command : commands) {
//            try {
//                executeCommand(command);
//            } catch (Exception ex) {
//                runSuccessful = false;
//                ex.printStackTrace();
//            }
//        }
//        return runSuccessful;
  }

  protected ApiResponse<UserCertificate> executeCommand(CommandType command) {
    apiClient.validateToken();
    CreateDirectoryEntry createDirectoryEntry = Transformer.getCreateDirectoryEntry(command);
    ApiResponse<UserCertificate> response = null;

    for (UserCertificate userCertificate : createDirectoryEntry.getUserCertificates()) {
      try {
        response = certificateAdministrationApi
            .modifyDirectoryEntryCertificateWithHttpInfo(userCertificate.getDn().getUid(),
                userCertificate.getDn().getCn(), userCertificate);
      } catch (ApiException e) {
        e.printStackTrace();
      }
      if (response.getStatusCode() == 200) {
        LOG.debug(
            "Modify directory entry execution successful operated\n" + response.getData());
      } else {
        throw new CommandException(
            "Modify directory entry execution failed. Response-Status was: " + response
                .getStatusCode() + "\n" + Transformer
                .getBaseDirectoryEntryFromCommandType(command));
      }
    }
    return response;
  }

  @Override
  public boolean postCheck() {
    try {
      super.postCheck();
      return true;
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return false;
  }
}
