/*
 * Copyright Shawn bower
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.libgrowl;

import net.sf.libgrowl.internal.GenericResponse;

public class SubscribeResponse extends GenericResponse {
	private final String SUBSCRIPTION_TTL = "Subscription-TTL";
	
	private String subscriptionTTL;
	
	public SubscribeResponse(String responseString) {
		super(responseString);
		setSubscriptionTTL(getCustomHeaders().get(SUBSCRIPTION_TTL));
		
	}

	private void setSubscriptionTTL(String subscriptionTTL) {
		this.subscriptionTTL = subscriptionTTL;
	}

	public String getSubscriptionTTL() {
		return subscriptionTTL;
	}
}
