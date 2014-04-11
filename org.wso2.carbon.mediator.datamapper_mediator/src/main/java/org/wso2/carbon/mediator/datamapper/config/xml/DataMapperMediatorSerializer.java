/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *   * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.wso2.carbon.mediator.datamapper.config.xml;

import org.apache.axiom.om.OMElement;
import org.apache.synapse.Mediator;
import org.apache.synapse.config.xml.*;
import org.wso2.carbon.mediator.datamapper.DataMapperMediator;

/**
 * This class acts as the serializer for the DataMapperMediator which will convert the
 * DataMapperMediator instance to the following xml configuration
 * 
 * <datamapper config="gov:datamapper/mappingConfig.dmc"
 *   inputSchema="gov:datamapper/inputSchema.avsc"
 *   outputSchema="gov:datamapper/outputSchema.avsc"
 *   inputType="application/xml"
     outputType="application/xml"
 *   /> 
 *
 */

public class DataMapperMediatorSerializer extends AbstractMediatorSerializer {

	/*private static final String SERIALIZATION_MESSAGE = Messages.DataMapperMediatorSerializer_serializationMessage;
	private static final String CONFIG_REGISTRYKEY = Messages.DataMapperMediatorSerializer_configRegistryKey;
	private static final String INPUTSCHEMA_REGISTRYKEY = Messages.DataMapperMediatorSerializer_inputSchemaRegistryKey;
	private static final String OUTPUTSHEMA_REGISTRYKEY = Messages.DataMapperMediatorSerializer_outputSchemaRegistryKey;*/

	
	/**
     * This uses to get the mediator class name which will be serialized by this serializer
     *
     * @return String representing the full class name of the mediator
     */
	@Override
	public String getMediatorClassName() {
		return DataMapperMediator.class.getName();
	}

	 /**
     * This method will implement the serialization logic of the DataMapperMediator class to the
     * relevant xml configuration
     *
     * @param An instance of DataMapperMediator to be serialized
     *
     * @return OMElement describing the serialized configuration of the DataMapperMediator
     */
	@Override
	protected OMElement serializeSpecificMediator(Mediator mediator) {
		if (!(mediator instanceof DataMapperMediator)) {
			//handleException(SERIALIZATION_MESSAGE //$NON-NLS-1$
			//		+ mediator.getType());
		}

		DataMapperMediator dataMapperMediator = (DataMapperMediator) mediator;
		OMElement dataMapperElement = fac
				.createOMElement(ConfigurationProperties.DATAMAPPER, synNS);

		if (dataMapperMediator.getConfigurationKey() != null) {
			// Serialize Value using ValueSerializer
			ValueSerializer keySerializer = new ValueSerializer();
			keySerializer.serializeValue(dataMapperMediator.getConfigurationKey(),
					ConfigurationProperties.CONFIG, dataMapperElement);
		} else {
			//handleException(CONFIG_REGISTRYKEY); //$NON-NLS-1$
		}

		if (dataMapperMediator.getInputSchemaKey() != null) {
			ValueSerializer keySerializer = new ValueSerializer();
			keySerializer.serializeValue(dataMapperMediator.getInputSchemaKey(),
					ConfigurationProperties.INPUTSCHEMA, dataMapperElement);
		} else {
			//handleException(INPUTSCHEMA_REGISTRYKEY); //$NON-NLS-1$
		}

		if (dataMapperMediator.getOutputSchemaKey() != null) {
			ValueSerializer keySerializer = new ValueSerializer();
			keySerializer.serializeValue(dataMapperMediator.getOutputSchemaKey(),
					ConfigurationProperties.OUTPUTSCHEMA, dataMapperElement);
		} else {
			//handleException(OUTPUTSHEMA_REGISTRYKEY); //$NON-NLS-1$
		}

		if (dataMapperMediator.getInputType() != null) {
			dataMapperElement.addAttribute(fac.createOMAttribute(ConfigurationProperties.INPUTTYPE, nullNS,dataMapperMediator.getInputType()));			
		} else {
			//handleException(OUTPUTSHEMA_REGISTRYKEY); //$NON-NLS-1$
		}
		if (dataMapperMediator.getOutputType() != null) {
			dataMapperElement.addAttribute(fac.createOMAttribute(ConfigurationProperties.OUTPUTTYPE, nullNS,dataMapperMediator.getOutputType()));
		} else {
			//handleException(OUTPUTSHEMA_REGISTRYKEY); //$NON-NLS-1$
		}
		
		
		saveTracingState(dataMapperElement, dataMapperMediator);

		return dataMapperElement;
	}

}
