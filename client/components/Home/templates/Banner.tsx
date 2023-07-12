import Link from 'next/link';

import { Button } from '../button/Button';
import { CTABanner } from '../cta/CTABanner';
import { Section } from '../layout/Section';

const Banner = () => (
	<Section>
		<CTABanner
			title='Supercharge your e-commerce game with VNPAYVouch now!'
			subtitle='Create a Campaign or Voucher!'
			button={
				<Link href='https://localhost:3000'>
					<a>
						<Button>Get Started</Button>
					</a>
				</Link>
			}
		/>
	</Section>
);

export { Banner };
