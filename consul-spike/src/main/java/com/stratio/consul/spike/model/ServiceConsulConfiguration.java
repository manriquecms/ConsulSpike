package com.stratio.consul.spike.model;

import java.util.Arrays;

public class ServiceConsulConfiguration {
	private String name;
	private String[] tags;
	private int port;
	private ServiceCheckConsul check;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public ServiceCheckConsul getCheck() {
		return check;
	}

	public void setCheck(ServiceCheckConsul check) {
		this.check = check;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((check == null) ? 0 : check.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + port;
		result = prime * result + Arrays.hashCode(tags);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceConsulConfiguration other = (ServiceConsulConfiguration) obj;
		if (check == null) {
			if (other.check != null)
				return false;
		} else if (!check.equals(other.check))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (port != other.port)
			return false;
		if (!Arrays.equals(tags, other.tags))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ServiceConsulConfiguration [name=" + name + ", tags="
				+ Arrays.toString(tags) + ", port=" + port + ", check=" + check
				+ "]";
	}
	
	
	public static class ServiceCheckConsul {
		private String script;
		private long interval;
	
		public String getScript() {
			return script;
		}
		
		public void setScript(String script) {
			this.script = script;
		}
		
		public long getInterval() {
			return interval;
		}
		
		public void setInterval(long interval) {
			this.interval = interval;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (interval ^ (interval >>> 32));
			result = prime * result
					+ ((script == null) ? 0 : script.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ServiceCheckConsul other = (ServiceCheckConsul) obj;
			if (interval != other.interval)
				return false;
			if (script == null) {
				if (other.script != null)
					return false;
			} else if (!script.equals(other.script))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "ServiceCheckConsul [script=" + script + ", interval="
					+ interval + "]";
		}
		
	}
}
