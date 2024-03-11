import { Given } from "@cucumber/cucumber"
import { Credential } from "@domain/core/Credential.domain"
import { NIF05World } from "cucumber/worlds/NIF05World"

Given('a valid credential with the next data', function (this: NIF05World, dataTable) {
    const record = dataTable.hashes()[0]
    this.credential = new Credential({ uniqueId: record.uniqueId })
})
