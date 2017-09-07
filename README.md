iibTrace-maven-plugin
=================

This open source plugin can be used to extract iib user traces during test execution.  


## Install to local maven repository

1. Install Maven
2. Clone git repo
3. Execute mvn install


## Add plugin to pom.xml

```xml
	<build>
		<plugins>
			<plugin>
				<groupId>com.exxeta.we.iib</groupId>
				<artifactId>iibTrace-maven-plugin</artifactId>
				<version>1.0</version>
				<executions>
					<execution>
						<id>pre-unit-test</id>
						<goals>
							<goal>prepare-trace</goal>
						</goals>
					</execution>
					<execution>
						<id>post-unit-test</id>
						<phase>test</phase>
						<goals>
							<goal>report</goal>
						</goals>
					</execution>
				</executions>
				<configuration>
					<mqsiProfile>${mqsi.dir}/mqsiprofile.cmd</mqsiProfile>
					<mqsiReadLog>${mqsi.dir}/mqsireadlog.exe</mqsiReadLog>
					<mqsiChangeTrace>${mqsi.dir}/mqsichangetrace.exe</mqsiChangeTrace>
					<broker>TESTNODE</broker>
					<executionGroup>EG_NAME</executionGroup>
					<reportDir>${project.build.directory}/iibTrace</reportDir>
				</configuration>
			</plugin>
		</plugins>
	</build>
```


## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request

## History

- 1.0   - Initial release


## Credits

- EXXETA AG
- See [Contributors](https://www.github.com/EXXETA/iibTrace-maven-plugin/graphs/contributors)

## License

Apache License v2.0