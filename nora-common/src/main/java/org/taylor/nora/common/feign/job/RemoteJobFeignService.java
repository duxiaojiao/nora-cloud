package org.taylor.nora.common.feign.job;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "nora-job")
public interface RemoteJobFeignService {
}
