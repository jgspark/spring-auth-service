package com.example.content.job

import com.example.content.config.AbstractJobTestConfiguration
import com.example.content.config.MainDataSourceTestConfiguration
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.Job
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig

@SpringJUnitConfig(
    classes = [
        RunningJob3Configuration::class,
        MainDataSourceTestConfiguration::class
    ]
)
class RunningJob3ConfigurationTest : AbstractJobTestConfiguration() {

    @Test
    fun `Step 테스트`() {

        val exec = jobLauncherTestUtils.launchStep("running3Step1")

        assertEquals(BatchStatus.COMPLETED, exec.status)
    }

    @Test
    fun `Running 잡 성공 테스트 케이스`(@Qualifier("running3Job") job: Job) {

        jobLauncherTestUtils.job = job

        val execution = jobLauncherTestUtils.launchJob()

        assertEquals(BatchStatus.COMPLETED, execution.status)

        val stepExecution = execution.stepExecutions.iterator().next()

        assertEquals(BatchStatus.COMPLETED, stepExecution.status)
        assertEquals(3, stepExecution.readCount)
        assertEquals(3, stepExecution.writeCount)
    }
}
